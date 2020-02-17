package screens;

import actions.*;
import entities.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import modules.*;
import programEntry.*;
import utils.*;
import validations.*;

import java.util.*;

public class DashboardScreen extends BaseToDoGuiScreen
{
	public static TaskUser user;
	private TableView<Task> table;
	private TableColumn<Task, String> titleColumn;
	private TableColumn<Task, String> name1Column;
	private TableColumn<Task, String> name2Column;
	private TableColumn<Task, String> textColumn;
	private TableColumn<Task, Boolean> completedColumn;
	private TableColumn<Task, Boolean> expiredColumn;
	private TableColumn<Task, Date> fromTimeColumn;
	private TableColumn<Task, Date> toTimeColumn;

	@Override
	public void init()
	{
		table = new TableView<>();
		table.getItems().clear();
		titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		name1Column = new TableColumn<>("Arabic Name");
		name1Column.setCellValueFactory(new PropertyValueFactory<>("name1"));
		name2Column = new TableColumn<>("English Name");
		name2Column.setCellValueFactory(new PropertyValueFactory<>("name2"));
		textColumn = new TableColumn<>("Text");
		textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
		completedColumn = new TableColumn<>("Is Completed?");
		completedColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
		expiredColumn = new TableColumn<>("Is Expired?");
		expiredColumn.setCellValueFactory(new PropertyValueFactory<>("expired"));
		fromTimeColumn = new TableColumn<>("From Time");
		fromTimeColumn.setCellValueFactory(new PropertyValueFactory<>("fromTime"));
		toTimeColumn = new TableColumn<>("To Time");
		toTimeColumn.setCellValueFactory(new PropertyValueFactory<>("toTime"));
		table.setOnMouseClicked(e -> {
			TablePosition<?, ?> element = Checker.getListFirstElement(table.getSelectionModel().getSelectedCells());
			if (element == null)
				return;
			TodoEntry.root.replaceChildren(new TaskEditorScreen(table.getItems().get(element.getRow())));
		});
		table.getColumns().addAll(titleColumn, name1Column, name2Column,fromTimeColumn,toTimeColumn, textColumn, completedColumn, expiredColumn);
	}

	@Override
	public void createView()
	{
		getChildren().add(table);
		try
		{
			List<Task> tasks = CoreLogic.getInstance().findUserTasks(user);
			for (Task task : tasks)
			{
				table.getItems().add(task);
			}
		}
		catch (Throwable e)
		{
			error(e.getMessage());
		}
	}

	@Override
	public void onSave(SaveButton saveButton)
	{

	}

	@Override
	public <T extends ButtonWithValidation> void beforeSaveAction(T button, TODOResponse response)
	{

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
		user = null;
		TodoEntry.root.replaceChildren(new LoginScreen());
	}
}
