// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2017, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
// end::copyright[]
package io.openliberty.guides.hello;

import com.cemetary.Deceased;
import com.cemetary.DeceasedList;
import com.cemetary.LoadDeceasedList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/servlet")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String PARAMETER_NAME="name";
    private static final String PARAMETER_STREET = "street";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // tag::responsestring[]

        DeceasedList dl = LoadDeceasedList.loadFromResources();

        String name = "";
        String street = "";

        List<Deceased> deceasedList = dl.deceasedList;
        String sortBy = request.getParameter("sort");
        if (Objects.equals(DeceasedList.SortBy.NAME.value, sortBy)) {
            dl.sortByName();
        } else if (Objects.equals(DeceasedList.SortBy.AGE.value, sortBy)) {
            dl.sortByAge();
        } else if (Objects.equals(DeceasedList.SortBy.DATE.value, sortBy)) {
            dl.sortByDate();
        } else if (Objects.equals(DeceasedList.SortBy.ADDRESS.value, sortBy)) {
            dl.sortByAddress();
        } else {
            System.out.println("No valid sort value: " + sortBy);
            name = request.getParameter(PARAMETER_NAME);
            System.out.println("name=" + name);

            if (name != null && !name.isBlank()) {
                deceasedList = dl.searchByName(name.trim());
                dl = new DeceasedList(deceasedList);
            }

            street = request.getParameter(PARAMETER_STREET);
            System.out.println("street=" + street);
            if (street != null && !street.isBlank()) {
                deceasedList = dl.searchByStreet(street.trim());
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append("<form name=\"deceased_list\" method=\"get\" action=\"servlet\">\n");
        sb.append(String.format(" name: <input type=\"text\" name=\"%s\" value=\"%s\"/>\n", PARAMETER_NAME, name));
        sb.append(String.format(" street: <input type=\"text\" name=\"%s\" value=\"%s\"/>\n", PARAMETER_STREET, street));
        sb.append("<input type=\"submit\" value=\"search\"/>");
        sb.append("</form>\n");

        sb.append("<table><tbody><tr>\n");
        sb.append("<th><a href='servlet?sort=name'>Name</a></th><th><a href='servlet?sort=date'>Date</a></th><th><a href='servlet?sort=age'>Age</a></th><th><a href='servlet?sort=street'>Street</a></th>");
        sb.append("\n</tr>\n");

        for (Deceased d: deceasedList) {
            sb.append("<tr>\n");
            sb.append(String.format("<td>%s</td><td>%s</td><td>%f</td><td>%s</td>", d.name, d.getFormattedDate(), d.age, d.address));
            sb.append("\n</tr>\n");

        }
        sb.append("</tbody></table>");
        response.getWriter().append(sb);
        // end::responsestring[]
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
}
