package app.controller;

import java.time.LocalDate;

public class Pay{
private Integer number;
private LocalDate duedate;
private Double payment;
private Double extraPay;
private Double interest;
private Double principle;
private Double balance;

public Pay() {

}
public Pay(int num, LocalDate due, double pay, double adPay, double interest, double prin,double balance) {
this.number = number;
this.duedate = duedate;
this.payment = payment;
this.extraPay = extraPay;
this.interest = interest;
this.principle = principle;
this.balance = balance;
}


public Integer getNumber() {
return number;
}


public void setNumber(Integer number) {
this.number = number;
}


public LocalDate getDuedate() {
return duedate;
}


public void setDuedate(LocalDate duedate) {
this.duedate = duedate;
}


public Double getPayment() {
return payment;
}


public void setPayment(Double payment) {
this.payment = payment;
}


public Double getExtraPay() {
return extraPay;
}


public void setExtraPay(Double extraPay) {
this.extraPay = extraPay;
}


public Double getInterest() {
return interest;
}


public void setInterest(Double interest) {
this.interest = interest;
}


public Double getPrinciple() {
return principle;
}


public void setPrinciple(Double principle) {
this.principle = principle;
}


public Double getBalance() {
return balance;
}


public void setBalance(Double balance) {
this.balance = balance;
}


public double getTotalPayments(double loanAmount, int terms, double intRate) {
intRate= intRate/1200;
int year = terms*12;
double numerator = intRate*loanAmount*year;
double denominator = 1-(Math.pow(1+intRate, -1*year));
double totalPayment = numerator/denominator;
double total = Math.round(totalPayment*100)/100;


return total;
}



}
