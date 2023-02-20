package data;

public class Bot {
    private final Integer botNumber;
    private Integer lowChip;
    private Integer highChip;

    public Bot(int botNumber) {
        this.botNumber = botNumber;
    }

    public Integer getBotNumber() {
        return botNumber;
    }

    public Integer getLowChip() {
        return lowChip;
    }

    public void setLowChip(Integer lowChip) {
        this.lowChip = lowChip;
    }

    public Integer getHighChip() {
        return highChip;
    }

    public void setHighChip(Integer highChip) {
        this.highChip = highChip;
    }

}
