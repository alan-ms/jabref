# Tests

## Class: NameFormatterTabViewModel
* Método: addFormatter

| D | Condição  | Situação para True  | Situação para False  |
|---|---|---|---|
|67|!StringUtil.isNullOrEmpty(addFormatterNameProperty.getValue())|addFormatterNameProperty.getValue()  != null AND addFormatterNameProperty.getValue()  != “”|addFormatterNameProperty.getValue() == null OR addFormatterNameProperty.getValue() == “”|
|67|!StringUtil.isNullOrEmpty(addFormatterStringProperty.getValue())|addFormatterStringProperty.getValue() != null AND addFormatterStringProperty.getValue() != “”|addFormatterStringProperty.getValue() == null OR addFormatterStringProperty.getValue() == “”|
