package com.pegboard.ui.views.peggame;

import java.util.HashMap;
import java.util.Map;

import com.pegboard.PegBoardController;
import com.pegboard.PositionState;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("peg-game")
@HtmlImport("src/views/peggame/peg-game.html")
@Route("")
public class PegGame extends PolymerTemplate<PegGame.PegGameModel> {

	private static final long serialVersionUID = 1L;

	@Id("ctrlBody")
	private VerticalLayout ctrlBody;

	private Icon[] m_icons;
	private Button[] m_buttons;
	private Map<Button, Integer> m_buttonPositions = new HashMap<>();
	private PegBoardController m_pegBoardController;

	public PegGame() {

		// Create peg board view elements.
		{
			m_icons = new Icon[15];
			m_buttons = new Button[15];
			for (int position = 0; position < 15; ++position) {
				Icon icon = new Icon(VaadinIcon.CLOSE);
				Button button = new Button(icon);
				m_buttonPositions.put(button, position);

				button.addClickListener(event -> {
					int clickPosition = m_buttonPositions.get(event.getSource());
					m_pegBoardController.click(clickPosition);
				});

				m_icons[position] = icon;
				m_buttons[position] = button;
			}

			HorizontalLayout row1 = new HorizontalLayout(m_buttons[0]);
			HorizontalLayout row2 = new HorizontalLayout(m_buttons[1], m_buttons[2]);
			HorizontalLayout row3 = new HorizontalLayout(m_buttons[3], m_buttons[4], m_buttons[5]);
			HorizontalLayout row4 = new HorizontalLayout(m_buttons[6], m_buttons[7], m_buttons[8], m_buttons[9]);
			HorizontalLayout row5 = new HorizontalLayout(m_buttons[10], m_buttons[11], m_buttons[12], m_buttons[13],
					m_buttons[14]);

			ctrlBody.add(row1, row2, row3, row4, row5);
		}

		// Create footer view elements.
		{
			Button buttonReset = new Button("Reset");

			buttonReset.addClickListener(event -> {
				m_pegBoardController.initialize();
			});

			ctrlBody.add(buttonReset);
		}

		// Set other view properties.
		{
			ctrlBody.setHeight("100vh"); // 100% of the viewport height.
			ctrlBody.setAlignItems(Alignment.CENTER);

		}

		// Create and initialize peg board controller.
		{
			m_pegBoardController = new PegBoardController();

			m_pegBoardController.regsterPositionStateListener((position, pegState) -> {

				// Update the button icon to correspond to the new position state.
				//
				m_icons[position].getElement().setAttribute("icon", getVaadinIconAttribute(pegState));
			});

			m_pegBoardController.initialize();
		}
	}

	private String getVaadinIconAttribute(PositionState pegState) {
		return "vaadin:" + getVaadinIcon(pegState).name().toLowerCase().replace('_', '-');
	}

	private VaadinIcon getVaadinIcon(PositionState pegState) {
		switch (pegState) {
		case OCCUPIED:
			return VaadinIcon.CIRCLE;
		case UNOCCUPIED:
			return VaadinIcon.CIRCLE_THIN;
		case SELECTED:
			return VaadinIcon.BULLSEYE;
		default:
			return VaadinIcon.CLOSE;
		}
	}

	public interface PegGameModel extends TemplateModel {

	}

}
