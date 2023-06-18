package io.pw.app.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 * Created by pwykowski
 */
@Route("")
public class MainView extends VerticalLayout {

	public MainView() {
		Button button = new Button("Add");
		TextField taskField = new TextField();

		VerticalLayout todoList = new VerticalLayout();

		button.addClickListener(buttonClickEvent -> {
			Notification.show("Clicked!");
			Checkbox task = new Checkbox(taskField.getValue());
			taskField.clear();
			todoList.add(task);
		});

		add(
				new H1("Welcome!"),
				todoList,
				new HorizontalLayout(taskField, button)
		);
	}

}
