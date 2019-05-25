package app.controller;



import app.StudentCalc;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import java.net.URL;

import java.sql.Date;

import java.time.LocalDate;

import java.time.Month;

import java.util.ResourceBundle;



import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

import javax.swing.text.TableView.TableCell;



import javafx.scene.control.cell.PropertyValueFactory;



import javafx.fxml.Initializable;

import javafx.beans.property.SimpleStringProperty;

import javafx.event.ActionEvent;

import javafx.scene.control.DatePicker;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;



public class LoanCalcViewController implements Initializable   {



private StudentCalc SC = null;



@FXML

private TextField LoanAmount; 



@FXML

private Label lblTotalPayemnts;



@FXML

private DatePicker PaymentStartDate;



@FXML

private TextField NbrOfYears;



@FXML

private TextField InterestRate;



@FXML

private Label lblTotalInterest;



@FXML

private TextField ExtraPayment;



@FXML

private TableView<Pay> tableView;



@FXML

private TableColumn numCol;



@FXML

private TableColumn dueDateCol;



@FXML

private TableColumn paymentCol;



@FXML

private TableColumn adPaymentCol;



@FXML

private TableColumn interestCol;



@FXML

private TableColumn principalCol;



@FXML

private TableColumn balanceCol;
@Override
public void initialize(URL location, ResourceBundle resources) {


numCol.setCellValueFactory(new PropertyValueFactory<Pay, Integer>("number"));
dueDateCol.setCellValueFactory(new PropertyValueFactory<Pay, LocalDate>("duedate"));
paymentCol.setCellValueFactory(new PropertyValueFactory<Pay, Double>("payment"));
adPaymentCol.setCellValueFactory(new PropertyValueFactory<Pay, Double>("extraPay"));
interestCol.setCellValueFactory(new PropertyValueFactory<Pay, Double>("interest"));
principalCol.setCellValueFactory(new PropertyValueFactory<Pay, Double>("principle"));
balanceCol.setCellValueFactory(new PropertyValueFactory<Pay, Double>("balance"));
 
  
}


public void setMainApp(StudentCalc sc) {
this.SC = sc;


}

/**
 * btnCalcLoan - Fire this event when the button clicks
 * 
 * @version 1.0
 * @param event
 */
@FXML
private void btnCalcLoan(ActionEvent event) {

//System.out.println("Amount: " + LoanAmount.getText());
//double dLoanAmount = Double.parseDouble(LoanAmount.getText());
//System.out.println("Amount: " + dLoanAmount);

//lblTotalPayemnts.setText("123");

//LocalDate localDate = PaymentStartDate.getValue();
 
//System.out.println(localDate);

Pay p = new Pay(); 

lblTotalPayemnts.setText((String.valueOf(p.getTotalPayments(Double.parseDouble(LoanAmount.getText()),

Integer.parseInt(NbrOfYears.getText()),

Double.parseDouble(InterestRate.getText())))));

lblTotalInterest.setText(String.valueOf(p.getTotalPayments(Double.parseDouble(LoanAmount.getText()),

Integer.parseInt(NbrOfYears.getText()),

Double.parseDouble(InterestRate.getText()))-Double.parseDouble(LoanAmount.getText())));

ObservableList<Pay> pay = FXCollections.observableArrayList();


Double balance = Double.parseDouble(LoanAmount.getText());
double extraPayment = Double.parseDouble(ExtraPayment.getText());
balance=balance-extraPayment;
int years = Integer.parseInt(NbrOfYears.getText());
int months = years*12;
double interestRate = Double.parseDouble(InterestRate.getText());
double totalPayment = p.getTotalPayments(balance,years,interestRate);
LocalDate date = PaymentStartDate.getValue();

double payment = totalPayment/months;
double interest;
double principal;

pay.add(new Pay(0,null,0,extraPayment,0,0,balance));

for(int i=1; i<=months;i++ ) {
interest=balance*interestRate/1200;
principal=payment-interest;
balance=balance-principal;
date=date.plusMonths(1);

if(balance<0) {
balance=0.0;
}

pay.add(new Pay(i,date,Math.round(payment*100)/100,0,Math.round(interest*100)/100,Math.round(principal*100)/100,Math.round(balance*100)/100));

}
tableView.setItems(pay);
}

}