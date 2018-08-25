package com.vaadin.flow.demo.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

/**
 * The main view contains a button and a template element.
 */
@HtmlImport("styles/shared-styles.html")
@Route("disabled")
public class MainView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private CustomerService service = CustomerService.getInstance();
	private Grid<Customer> gridCustomerList;
	private TextField textFilterText;
//	private CustomerForm formCustomerDetail;

	public MainView() {

		// Construct child controls
		{
			HorizontalLayout layoutToolbar;
			{
				HorizontalLayout layoutFilter;
				{
					textFilterText = new TextField();
					textFilterText.setPlaceholder("Filter by name...");
					textFilterText.setValueChangeMode(ValueChangeMode.EAGER);
					textFilterText.addValueChangeListener(e -> updateList());

					Button buttonFilterClear = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE));
					buttonFilterClear.addClickListener(e -> {
						textFilterText.clear();
					});

					layoutFilter = new HorizontalLayout(textFilterText, buttonFilterClear);
				}

				Button buttonAddCustomer = new Button("Add new customer");
				buttonAddCustomer.addClickListener(e -> {
					gridCustomerList.asSingleSelect().clear();
//					formCustomerDetail.setCustomer(new Customer());
				});

				layoutToolbar = new HorizontalLayout(layoutFilter, buttonAddCustomer);
			}

			HorizontalLayout layoutBody;
			{
				gridCustomerList = new Grid<>();
				gridCustomerList.setSizeFull();
				gridCustomerList.addColumn(Customer::getFirstName).setHeader("First name");
				gridCustomerList.addColumn(Customer::getLastName).setHeader("Last name");
				gridCustomerList.addColumn(Customer::getStatus).setHeader("Status");

//				formCustomerDetail = new CustomerForm(this);

				layoutBody = new HorizontalLayout(gridCustomerList);
				layoutBody.setAlignItems(Alignment.START);
				layoutBody.setSizeFull();
			}

			String sessionId = VaadinSession.getCurrent().getSession().getId();

			Label textSessionId = new Label();
			textSessionId.setText(sessionId);

			this.add(layoutToolbar, layoutBody, textSessionId);
		}

		this.setHeight("100vh"); // 100% of the viewport height.
		updateList();

		gridCustomerList.asSingleSelect().addValueChangeListener(event -> {
//			formCustomerDetail.setCustomer(event.getValue());
		});
	}

	public void updateList() {
		gridCustomerList.setItems(service.findAll(textFilterText.getValue()));
	}
}