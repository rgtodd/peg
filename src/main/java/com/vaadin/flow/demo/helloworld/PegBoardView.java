/*
 * Copyright 2000-2018 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.demo.helloworld;

import com.peg.model.PegBoard;
import com.peg.model.PegState;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a template element.
 */
@HtmlImport("styles/shared-styles.html")
@Route("")
public class PegBoardView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private Icon[] m_icons;
	private PegBoard m_pegBoard;

	public PegBoardView() {

		m_icons = new Icon[15];
		for (int idx = 0; idx < 15; ++idx) {
			m_icons[idx] = new Icon(VaadinIcon.CLOSE);
		}

		HorizontalLayout row1 = new HorizontalLayout(m_icons[0]);
		HorizontalLayout row2 = new HorizontalLayout(m_icons[1], m_icons[2]);
		HorizontalLayout row3 = new HorizontalLayout(m_icons[3], m_icons[4], m_icons[5]);
		HorizontalLayout row4 = new HorizontalLayout(m_icons[6], m_icons[7], m_icons[8], m_icons[9]);
		HorizontalLayout row5 = new HorizontalLayout(m_icons[10], m_icons[11], m_icons[12], m_icons[13], m_icons[14]);

		add(row1, row2, row3, row4, row5);

		m_pegBoard = new PegBoard();
		m_pegBoard.regsterPegStateListener((position, pegState) -> {
			m_icons[position].getElement().setAttribute("icon",
					"vaadin:" + getVaadinIcon(pegState).name().toLowerCase().replace('_', '-'));
		});

		m_pegBoard.initialize();

		setHeight("100vh"); // 100% of the viewport height.
		setAlignItems(Alignment.CENTER);
	}

	private VaadinIcon getVaadinIcon(PegState pegState) {
		switch (pegState) {
		case OCCUPIED:
			return VaadinIcon.CIRCLE;
		case UNOCCUPIED:
			return VaadinIcon.CIRCLE_THIN;
		default:
			return VaadinIcon.CLOSE;
		}
	}
}
