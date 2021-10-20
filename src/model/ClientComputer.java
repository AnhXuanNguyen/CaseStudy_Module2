package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ClientComputer extends Computer implements ComputerCando, GetTotalTimeAndCash, Serializable {
    private static final long CASH_ON_HOUR = 50000;
    private LocalDateTime openTime;
    private Customer customer;

    public ClientComputer(int i) {
        super.setCode("MAY"+i);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }
    @Override
    public void withDrawMoneyFromCustomer(){
        long cash = customer.getDepositMoney() - totalCash();
        customer.setDepositMoney(cash);
        if (cash <= 0){
            close();
        }
        else withDrawMoneyFromCustomer();
    }
    @Override
    public long totalCash() {
        return getUseTime()* CASH_ON_HOUR;
    }
    @Override
    public long getUseTime() {
        LocalDateTime time = LocalDateTime.now();
        customer.setLoginDay(time);
        long day = time.getDayOfMonth() - openTime.getDayOfMonth();
        day = day*24;
        return time.getHour() - openTime.getHour() + day;
    }

    @Override
    public void open() {
        super.setStatus(true);
        openTime = LocalDateTime.now();
        withDrawMoneyFromCustomer();
    }

    @Override
    public void close() {
        customer = null;
        openTime = null;
        super.setStatus(false);
    }
    public String toString(){
        return (!super.isStatus()?getCode()+": Disnable":customer.toString()+"| "+super.getCode()+" | Thời gian mở máy: "+this.openTime+"| Tổng thời gian chơi: "+this.getUseTime());
    }
}