package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * Класс приложения
 */
public class MySampleApplication implements EntryPoint
{

    /**
     * Точка входа
     */
    public void onModuleLoad()
    {
        final Button button = new Button("Click me");
        final Label label = new Label();

        button.addClickHandler(event -> {
            if (label.getText().equals("")) {
                MySampleApplicationService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
            } else {
                label.setText("");
            }
        });

        // Инициализируем калькулятор
        final Calculator calculator = new Calculator();

        // Кнопка с надписью "=" - вызывает вывод результата вычисления
        final Button equalsButton = new Button(" = ");

        // Выпадающий список доступных операций
        final ListBox operationBox = new ListBox();

        // Добавляем возможные операции в выпадающий список
        calculator.supportedOperations().forEach(operationBox::addItem);

        // Поля ввода первого и второго числа
        final DoubleBox argBox1 = new DoubleBox();
        final DoubleBox argBox2 = new DoubleBox();

        // Поле вывода результата
        final Label output = new Label();

        // Меняем поле вывода (отображаем результат)
        equalsButton.addClickHandler(
                e ->
                {
            Double result = calculator.calculate(
                    operationBox.getSelectedValue(),
                    argBox1.getValue(),
                    argBox2.getValue()
            );
            output.setText(String.valueOf(result));
                });

        // Связываем объекты HTML с java сущностями
        RootPanel.get("argSlot1").add(argBox1);
        RootPanel.get("argSlot2").add(argBox2);
        RootPanel.get("operationSlot").add(operationBox);
        RootPanel.get("buttonSlot").add(equalsButton);
        RootPanel.get("outputSlot").add(output);
    }

    private static class MyAsyncCallback implements AsyncCallback<String>
    {
        private Label label;

        MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
