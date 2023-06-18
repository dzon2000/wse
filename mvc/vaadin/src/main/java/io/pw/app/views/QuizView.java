package io.pw.app.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProviderListener;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializableComparator;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pwykowski
 */

@Route("/quiz")
public class QuizView extends VerticalLayout {

	private Map<String, Integer> votes = new HashMap<>();

	public QuizView() {
		CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
		checkboxGroup.setLabel("Preferred browser");
		checkboxGroup.setItems(
				"Chrome",
				"Firefox",
				"Safari",
				"Edge",
				"Brave"
		);
		checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

		Grid<Vote> grid = new Grid<>(Vote.class, false);
		grid.addColumn(Vote::browser).setHeader("Browser");
		grid.addColumn(Vote::amount).setHeader("Votes").setSortable(true);
		grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

		Button voteButton = new Button("Vote", buttonClickEvent -> {
			Notification.show("Thank you for you vote!");
		});
		voteButton.addClickListener(click -> {
			checkboxGroup.getSelectedItems().forEach(vote -> {
				final Integer currentVotes = votes.getOrDefault(vote, 0);
				votes.put(vote, currentVotes + 1);

				final List<Vote> voteList = votes.entrySet().stream().map((v) -> new Vote(v.getKey(), v.getValue())).collect(Collectors.toList());
				grid.setItems(voteList);
			});
			checkboxGroup.deselectAll();
		});

		TextField firstName = new TextField("First name");
		TextField lastName = new TextField("Last name");
		TextField username = new TextField("Username");
		PasswordField password = new PasswordField("Password");
		PasswordField confirmPassword = new PasswordField("Confirm password");

		FormLayout formLayout = new FormLayout();
		formLayout.add(firstName, lastName, username, password,
				confirmPassword);
		formLayout.setResponsiveSteps(
				// Use one column by default
				new FormLayout.ResponsiveStep("0", 1),
				// Use two columns, if layout's width exceeds 500px
				new FormLayout.ResponsiveStep("500px", 2));
// Stretch the username field over 2 columns
		formLayout.setColspan(username, 2);

		add(
				new H1("What's your browser of choice?"),
				checkboxGroup,
				voteButton,
				grid, formLayout
		);

	}

	record Vote(String browser, int amount) {

	}
}
