package com.vaadin.flow.demo.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

@SuppressWarnings("serial")
public class CustomerForm extends FormLayout {

	// Bound controls
	//
	private TextField firstName;
	private TextField lastName;
	private ComboBox<CustomerStatus> status;

	private Button buttonSave;
	private Button buttonDelete;

	private CustomerService service = CustomerService.getInstance();
	private Customer customer;
	private MainView view;

	private Binder<Customer> binder = new Binder<>(Customer.class);

	public CustomerForm(MainView view) {
		this.view = view;

		// Construct child controls
		{
			firstName = new TextField("First name");
			lastName = new TextField("Last name");
			status = new ComboBox<>("Status");

			HorizontalLayout layoutButtons;
			{
				buttonSave = new Button("Save");
				buttonSave.getElement().setAttribute("theme", "primary");
				buttonSave.addClickListener(e -> this.save());

				buttonDelete = new Button("Delete");
				buttonDelete.addClickListener(e -> this.delete());

				layoutButtons = new HorizontalLayout(buttonSave, buttonDelete);
			}

			add(firstName, lastName, status, layoutButtons);
		}

		status.setItems(CustomerStatus.values());

		binder.bindInstanceFields(this);

		setCustomer(null);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		binder.setBean(customer);
		boolean enabled = customer != null;
		buttonSave.setEnabled(enabled);
		buttonDelete.setEnabled(enabled);
		if (enabled) {
			firstName.focus();
		}
	}

	private void delete() {
		service.delete(customer);
//		view.updateList();
		setCustomer(null);
	}

	private void save() {
		service.save(customer);
//		view.updateList();
		setCustomer(null);
	}
}
