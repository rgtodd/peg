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

	public PegBoardView() {

		HorizontalLayout row1;
		{
			Icon peg1 = VaadinIcon.CIRCLE.create();
			row1 = new HorizontalLayout(peg1);
//			row1.setJustifyContentMode(JustifyContentMode.EVENLY);

		}

		HorizontalLayout row2;
		{
			Icon peg2 = VaadinIcon.CIRCLE.create();
			Icon peg3 = VaadinIcon.CIRCLE.create();
			row2 = new HorizontalLayout(peg2, peg3);
//			row1.setJustifyContentMode(JustifyContentMode.EVENLY);
		}

		HorizontalLayout row3;
		{
			Icon peg4 = VaadinIcon.CIRCLE.create();
			Icon peg5 = VaadinIcon.CIRCLE.create();
			Icon peg6 = VaadinIcon.CIRCLE.create();
			row3 = new HorizontalLayout(peg4, peg5, peg6);
//			row1.setJustifyContentMode(JustifyContentMode.EVENLY);
		}

		this.add(row1, row2, row3);

		this.setHeight("100vh"); // 100% of the viewport height.
		this.setAlignItems(Alignment.CENTER);
	}
}
