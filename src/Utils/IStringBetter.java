package Utils;

public interface IStringBetter {
    String getStr();

    void setStr(String str);

    StringBetter repeate(int n);

    StringBetter append(String strA);

    StringBetter append(StringBetter strA);

    StringBetter black();

    StringBetter red();

    StringBetter green();

    StringBetter orange();

    StringBetter blue();

    StringBetter roxo();

    StringBetter cyan();

    StringBetter grey();

    StringBetter white();

    StringBetter bold();

    StringBetter under();

    StringBetter blink();

    StringBetter RESET();

    StringBetter hide_cursor();

    StringBetter show_cursor();

    String toString();
}
