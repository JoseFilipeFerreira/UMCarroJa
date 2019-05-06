package Utils;

public class StringBetter implements IStringBetter{
    private String str;

    public StringBetter(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public StringBetter repeate(int n){
        String s = "";
        for(int i = 0; i < n; i++)
            s += this.str;
        return new StringBetter(s);
    }

    public StringBetter append(String strA){
        this.str += strA;
        return this;
    }

    public StringBetter append(StringBetter strA){
        this.str += strA.str;
        return this;
    }

    public StringBetter black() {
        return new StringBetter("\033[30m" + this.str).RESET();
    }

    public StringBetter red() {
        return new StringBetter("\033[31m" + this.str).RESET();

    }

    public StringBetter green() {
        return new StringBetter("\033[32m" + this.str).RESET();

    }

    public StringBetter orange() {
        return new StringBetter("\033[33m" + this.str).RESET();

    }

    public StringBetter blue() {
        return new StringBetter("\033[34m" + this.str).RESET();

    }

    public StringBetter roxo() {
        return new StringBetter("\033[35m" + this.str).RESET();

    }

    public StringBetter cyan() {
        return new StringBetter("\033[36m" + this.str).RESET();

    }

    public StringBetter grey(){
        return new StringBetter("\033[37m" + this.str).RESET();

    }

    public StringBetter white() {
        return new StringBetter( "\033[38m" + this.str).RESET();

    }

    public StringBetter bold() {
        return new StringBetter("\033[1m" + this.str).RESET();

    }

    public StringBetter under(){
        return new StringBetter("\033[4m" + this.str).RESET();

    }

    public StringBetter blink(){
        return new StringBetter( "\033[5m" + this.str).RESET();
    }

    public StringBetter RESET(){
        return new StringBetter(this.str + "\033[0m");
    }

    public StringBetter hide_cursor(){
        return new StringBetter(this.str + "\033[?25l");
    }

    public StringBetter show_cursor(){
        return new StringBetter(this.str + "\033[?25h");
    }

    @Override
    public String toString() {
        return this.str;
    }
}
