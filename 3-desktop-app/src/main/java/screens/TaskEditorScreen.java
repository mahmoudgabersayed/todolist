package screens;

import actions.*;
import entities.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import modules.*;
import programEntry.*;
import utils.*;
import validations.*;

import java.time.*;
import java.util.*;

public class TaskEditorScreen extends BaseToDoGuiScreen
{
	private static String SAVE_BUTTON = "@task_editor_screen_save_button@";
	private static String DELETE_BUTTON = "@task_editor_screen_delete_button@";
	private static String BACK_BUTTON = "@task_editor_screen_back_button@";

	private TextField codeController;
	private TextField name1Controller;
	private TextField name2Controller;
	private TextField taskOrderController;
	private TextArea textController;
	private CheckBox completed;
	private DatePicker fromTime;
	private DatePicker toTime;
	private SaveButton save;
	private SaveButton delete;
	private SaveButton back;

	private Task task;

	public TaskEditorScreen(Task task)
	{
		this.task = task == null ? new Task() : task;
		init();
		createView();
	}

	@Override
	public void init()
	{
		if (task == null)
			return;
		codeController = new TextField();
		name1Controller = new TextField();
		name2Controller = new TextField();
		taskOrderController = new TextField();
		textController = new TextArea();
		completed = new CheckBox();
		fromTime = new DatePicker();
		fromTime.setValue(LocalDate.now());
		toTime = new DatePicker();
		toTime.setValue(LocalDate.now());
		save = new SaveButton(SAVE_BUTTON, "Save", this);
		delete = new SaveButton(DELETE_BUTTON, "Delete", this);
		back = new SaveButton(BACK_BUTTON, "Back", this);
		readFromTask();
	}

	@Override
	public void createView()
	{
		if (task == null)
			return;
		GridPane infoGrid = new GridPane();
		getChildren().add(infoGrid);
		infoGrid.setHgap(20);
		infoGrid.setVgap(10);
		int rowIndex = 0;
		infoGrid.add(new Label("Code"), 0, rowIndex);
		infoGrid.add(codeController, 1, rowIndex++);
		infoGrid.add(new Label("Arabic Name"), 0, rowIndex);
		infoGrid.add(name1Controller, 1, rowIndex++);
		infoGrid.add(new Label("English Name"), 0, rowIndex);
		infoGrid.add(name2Controller, 1, rowIndex++);
		infoGrid.add(new Label("Task Order"), 0, rowIndex);
		infoGrid.add(taskOrderController, 1, rowIndex++);
		infoGrid.add(new Label("Is Completed"), 0, rowIndex);
		infoGrid.add(completed, 1, rowIndex++);
		infoGrid.add(new Label("From Time"), 0, rowIndex);
		infoGrid.add(fromTime, 1, rowIndex++);
		infoGrid.add(new Label("To Time"), 0, rowIndex);
		infoGrid.add(toTime, 1, rowIndex++);
		Label notesLabel = new Label("To do Notes");
		GridPane.setConstraints(notesLabel, 0, rowIndex, 2, 1);
		GridPane.setHalignment(notesLabel, HPos.CENTER);
		infoGrid.add(notesLabel, 0, rowIndex++);
		GridPane.setConstraints(textController, 0, rowIndex, 2, 1);
		GridPane.setHalignment(textController, HPos.CENTER);
		infoGrid.add(textController, 0, rowIndex++);
		HBox box = new HBox(10);
		box.getChildren().addAll(back, delete, save);
		GridPane.setHalignment(box, HPos.CENTER);
		infoGrid.add(box, 0, rowIndex);
	}

	@Override
	public void onSave(SaveButton saveButton)
	{
		TodoEntry.root.replaceChildren(new DashboardScreen());
	}

	@Override
	public <T extends ButtonWithValidation> void beforeSaveAction(T button, TODOResponse response)
	{
		if (Checker.areEqual(button.getUiId(), BACK_BUTTON))
			return;
		if (Checker.areEqual(button.getUiId(), DELETE_BUTTON))
		{
			CoreLogic.getInstance().deleteTask(task).addToResponse(response);
		}
		if (Checker.areEqual(button.getUiId(), SAVE_BUTTON))
		{
			writeToTask();
			CoreLogic.getInstance().addOrUpdateTask(task).addToResponse(response);
		}
	}

	@Override
	public List<Menu> buildMenus()
	{
		Menu task = new Menu("Task");
		MenuItem addTask = new MenuItem("Add Task");
		addTask.setOnAction(e -> {
			TodoEntry.root.replaceChildren(new TaskEditorScreen(null));
		});
		task.getItems().addAll(addTask);
		Menu more = new Menu("More");
		MenuItem admin = new MenuItem("Admin");
		admin.setOnAction(e -> {
			//TODO : go to admin view (later feature)
		});
		MenuItem logout = new MenuItem("Logout");
		logout.setOnAction(e -> {
			logoutCurrentUser();
		});
		more.getItems().addAll(admin, logout);
		return Arrays.asList(task, more);
	}

	private void logoutCurrentUser()
	{
		DashboardScreen.user = null;
		TodoEntry.root.replaceChildren(new LoginScreen());
	}

	private void readFromTask()
	{
		codeController.setText(task.getCode());
		name1Controller.setText(task.getName1());
		name2Controller.setText(task.getName2());
		taskOrderController.setText(task.getTaskOrder() + "");
		textController.setText(task.getText());
		completed.setSelected(task.isCompleted());
		if (task.getFromTime() != null)
			fromTime.setValue(LocalDate.ofEpochDay(task.getFromTime().getTime()));
		if (task.getToTime() != null)
			toTime.setValue(LocalDate.ofEpochDay(task.getToTime().getTime()));
	}

	private void writeToTask()
	{
		task.setCode(codeController.getText());
		task.setName1(name1Controller.getText());
		task.setName2(name2Controller.getText());
		task.setTaskOrder(Integer.parseInt(taskOrderController.getText()));
		task.setText(textController.getText());
		task.setCompleted(completed.isSelected());
		task.setFromTime(new Date(fromTime.getValue().toEpochDay()));
		task.setToTime(new Date(toTime.getValue().toEpochDay()));
		task.setUser(DashboardScreen.user);
	}
}
