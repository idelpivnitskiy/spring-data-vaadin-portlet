package org.vaadin.springportlet;

import com.liferay.portal.model.User;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.vaadin.springportlet.backend.Book;
import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

@org.springframework.stereotype.Component
@Scope(proxyMode = ScopedProxyMode.NO,value = "prototype")
public class BookForm extends AbstractForm<Book> {
	
	@Autowired
	LibraryPortletUserService service;

    DateField publishDate = new DateField("Publish Date");

    TextField name = new MTextField("Name").withFullWidth();

    MTextArea description = new MTextArea("Description").withFullWidth();
    
    TypedSelect<String> loanedBy = new TypedSelect<String>("Loaned by")
    		.withSelectType(ComboBox.class);

    @Override
    protected Component createContent() {

    	loanedBy.setOptions(service.getCompanyUserEmails());
    	
        return new MVerticalLayout(
                new FormLayout(
                        name,
                        description,
                        publishDate,
                        loanedBy
                ),
                getToolbar()
        );
    }
    
    @Override
    public Window openInModalPopup() {
    	Window popup = super.openInModalPopup();
    	popup.setWidth("70%");
		return popup;
    }

}
