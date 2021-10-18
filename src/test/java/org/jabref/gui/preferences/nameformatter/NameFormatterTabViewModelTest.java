package org.jabref.gui.preferences.nameformatter;

import java.lang.reflect.Field;
import java.util.Collections;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import org.jabref.gui.DialogService;
import org.jabref.preferences.PreferencesService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class NameFormatterTabViewModelTest {

    private NameFormatterTabViewModel nameFormatterTabViewModel;

    @BeforeEach
    public void setUp() {
        PreferencesService preferencesService = mock(PreferencesService.class);
        DialogService dialogService = mock(DialogService.class);
        nameFormatterTabViewModel = new NameFormatterTabViewModel(dialogService, preferencesService);
    }

    @Test
    public void addFormatterTest() throws NoSuchFieldException, IllegalAccessException {
        // addFormatterNameProperty == null && addFormatterStringProperty == null
        nameFormatterTabViewModel.addFormatter();
        Assertions.assertEquals(Collections.emptyList(), nameFormatterTabViewModel.formatterListProperty());

        // addFormatterNameProperty == 'test1' && addFormatterStringProperty == null
        StringProperty addFormatterNameProperty = new SimpleStringProperty("test1");
        StringProperty addFormatterStringProperty = new SimpleStringProperty(null);
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterNameProperty", addFormatterNameProperty);
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterStringProperty", addFormatterStringProperty);
        nameFormatterTabViewModel.addFormatter();
        Assertions.assertEquals(Collections.emptyList(), nameFormatterTabViewModel.formatterListProperty());

        // addFormatterNameProperty == null && addFormatterStringProperty == 'test2'
        addFormatterNameProperty = new SimpleStringProperty(null);
        addFormatterStringProperty = new SimpleStringProperty("test2");
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterNameProperty", addFormatterNameProperty);
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterStringProperty", addFormatterStringProperty);
        nameFormatterTabViewModel.addFormatter();
        Assertions.assertEquals(Collections.emptyList(), nameFormatterTabViewModel.formatterListProperty());

        // addFormatterNameProperty == '' && addFormatterStringProperty == ''
        addFormatterNameProperty = new SimpleStringProperty("");
        addFormatterStringProperty = new SimpleStringProperty("");
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterNameProperty", addFormatterNameProperty);
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterStringProperty", addFormatterStringProperty);
        nameFormatterTabViewModel.addFormatter();
        Assertions.assertEquals(Collections.emptyList(), nameFormatterTabViewModel.formatterListProperty());

        // addFormatterNameProperty == '' && addFormatterStringProperty == 'test2'
        addFormatterNameProperty = new SimpleStringProperty("");
        addFormatterStringProperty = new SimpleStringProperty("test2");
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterNameProperty", addFormatterNameProperty);
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterStringProperty", addFormatterStringProperty);
        nameFormatterTabViewModel.addFormatter();
        Assertions.assertEquals(Collections.emptyList(), nameFormatterTabViewModel.formatterListProperty());

        // addFormatterNameProperty == 'test1' && addFormatterStringProperty == ''
        addFormatterNameProperty = new SimpleStringProperty("test1");
        addFormatterStringProperty = new SimpleStringProperty("");
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterNameProperty", addFormatterNameProperty);
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterStringProperty", addFormatterStringProperty);
        nameFormatterTabViewModel.addFormatter();
        Assertions.assertEquals(Collections.emptyList(), nameFormatterTabViewModel.formatterListProperty());

        // addFormatterNameProperty == 'test1' && addFormatterStringProperty == 'test2'
        ListProperty<NameFormatterItemModel> formatterListPropertyExpected = new SimpleListProperty<>(FXCollections.observableArrayList());
        formatterListPropertyExpected.add(new NameFormatterItemModel(
                "test1", "test2"));
        addFormatterNameProperty = new SimpleStringProperty("test1");
        addFormatterStringProperty = new SimpleStringProperty("test2");
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterNameProperty", addFormatterNameProperty);
        this.setFinalStaticField(nameFormatterTabViewModel, "addFormatterStringProperty", addFormatterStringProperty);
        nameFormatterTabViewModel.addFormatter();
        Assertions.assertEquals(1, nameFormatterTabViewModel.formatterListProperty().size());
        Assertions.assertEquals("test2", nameFormatterTabViewModel.formatterListProperty().get(0).getFormat());
        Assertions.assertEquals("test2", nameFormatterTabViewModel.formatterListProperty().get(0).getFormat());
        Assertions.assertEquals("", nameFormatterTabViewModel.addFormatterNameProperty().getValue());
        Assertions.assertEquals("", nameFormatterTabViewModel.addFormatterStringProperty().getValue());
    }

    private void setFinalStaticField(NameFormatterTabViewModel nameFormatterTabViewModel, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = nameFormatterTabViewModel.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(nameFormatterTabViewModel, value);
    }
}
