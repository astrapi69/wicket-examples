/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;


@MountPath("public/swap/person")
public class PersonPage extends PubliclyBasePage<PersonBean>
{
	private static final long serialVersionUID = 1L;

	public PersonPage(final PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	public Component newContainerPanel(final String id, final IModel<PersonBean> model)
	{
		if(model == null) {
			return new PersonPanel(id, Model.of(PersonBean.builder().firstName("")
				.lastName("").age("").gender("").build()));
		}
		return new PersonPanel(id, model);
	}
}
