package AccountEntry;

public class AccountEntry {
    private String date;
    private String purpose;
    private boolean isIncome;
    private int amount;
    private String detail;

    public AccountEntry(String date, String purpose, boolean isIncome, int amount, String detail) {
        this.date = date;
        this.purpose = purpose;
        this.isIncome = isIncome;
        this.amount = amount;
        this.detail = detail;
    }

    public void printAll(){
        System.out.println("날짜 : " +date + " 목적 : " + purpose + " 수입(true) or 지출(false) : " + isIncome
        + " 총액 : " + amount + " 상세 : " + detail);
    }

    public String getAll(){
        return this.date + "-" + this.purpose + "-" + this.isIncome + "-" + this.amount + "-" + this.detail;

    }
    public String getDate() {
        return date;
    }

    public String getPurpose() {
        return purpose;
    }

    public boolean getIsIncome() {
        return isIncome;
    }


    public int getAmount() {
        return amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setIsIncome(boolean isIncome) {
        this.isIncome = isIncome;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
