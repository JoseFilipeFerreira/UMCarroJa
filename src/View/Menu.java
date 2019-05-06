package View;
import Utils.StringBetter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.System.out;

public class Menu implements IMenu{
    private MenuInd menu;
    private Stack<MenuInd> prev;
    private ArrayList<MenuInd> options;
    private boolean run;

    public enum MenuInd {
        Categories,
        Static,
        Dynamic,
        Q1,
        Q2,
        Q3,
        Q4,
        Q5,
        Q6,
        Q7,
        Q8,
        Q9,
        Q10,
        Q1_1,
        Q1_2
    }

    public Menu() {
        this.menu = MenuInd.Categories;
        this.prev = new Stack<>();
        this.options = new ArrayList<>();
        this.run = true;
        this.correctMenu();
    }

    public Menu(MenuInd menuInd) {
        this.menu = menuInd;
        this.prev = new Stack<>();
        this.options = new ArrayList<>();
        this.run = true;
        this.correctMenu();
    }

    public MenuInd getMenu() {
        return this.menu;
    }

    public String getInputClient(){
        Scanner scanner = new Scanner(System.in);
        boolean error = false;
        while(true){
            out.print("\033\143");
            out.println(this.createHeader());
            if (error)
                out.println(new StringBetter("Cliente Inválido").under().append("\n").toString());
            else
                out.println();
            out.println("Inserir cliente: ");
            return scanner.nextLine();
        }
    }

    public String getInputProduto(){
        Scanner scanner = new Scanner(System.in);
        boolean error = false;
        while(true){
            out.print("\033\143");
            out.println(this.createHeader());
            if (error)
                out.println(new StringBetter("Produto Inválido").under().toString());
            else
                out.println();
            out.println("Inserir Produto: ");
            return scanner.nextLine();
        }
    }

    public int getInputInteiro(){
        Scanner scanner = new Scanner(System.in);
        String str;
        boolean error = false;
        while(true){
            out.print("\033\143");
            out.println(this.createHeader());
            if (error)
                out.println(new StringBetter("Inteiro Inválido").under().toString());
            else
                out.println();
            out.println("Inserir Inteiro: ");
            str = scanner.nextLine();
            if (str.matches("^[+-]?\\d{1,8}$"))
                return Integer.parseInt(str);
            else
                error = true;
        }
    }

    public int getInputMes(){
        Scanner scanner = new Scanner(System.in);
        String str;
        boolean error = false;
        while(true){
            out.print("\033\143");
            out.println(this.createHeader());
            if (error)
                out.println(new StringBetter("Mês Inválido").under().toString());
            else
                out.println();
            out.println("Inserir Mês: ");
            str = scanner.nextLine();
            if (str.matches("^[1-9]|1[0-2]$"))
                return Integer.parseInt(str);
            else
                error = true;
        }
    }

    public Menu parser(String str){
        if (str.matches("^[+-]?\\d{1,8}$")) {
            this.selectOption(Integer.parseInt(str));
        }
        switch (str){
            case "b":
            case "..":
                this.back();
                break;
            case "e":
                this.run = false;
                break;
        }

        return this;
    }

    public boolean getRun() {
        return this.run;
    }

    public Menu selectOption(int i){
        if (this.options.size() > i - 1) {
            this.prev.push(this.menu);
            this.menu = this.options.get(i - 1);
            this.correctMenu();
        }
        return this;
    }

    public Menu back(){
        if (this.prev.size() > 0) {
            this.menu = this.prev.pop();
            this.correctMenu();
        }
        else {
            this.run = false;
        }
        return this;
    }

    public String createHeader(){
        StringBetter strHeader = new StringBetter("\t--");
        for (MenuInd val : this.prev)
            strHeader.append(val.name()).append("/");

        return strHeader.append(this.menu.name()).append("--\n").red().toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\033\143");
        s.append(this.createHeader()).append("\n\n");

        for(int i = 0; i < this.options.size(); i++)
            s.append(i + 1).append("- ").append(this.menuOptionText(i)).append("\n");
        s.append("\n");
        return s.toString();
    }

    private String menuOptionText(int i) {
        String r = "";
        switch (this.options.get(i)){
            case Categories:
                r += "Menu Inicial";
                break;
            case Static:
                r += "Queries estáticas";
                break;
            case Dynamic:
                r += "Queries dinâmicas";
                break;
            case Q1:
                r += "Produtos não comprados";
                break;
            case Q2:
                r += "Total de vendas e clientes distintos";
                break;
            case Q3:
                r += "Stats sobre cliente (ano)";
                break;
            case Q4:
                r += "Stats sobre produto (ano)";
                break;
            case Q5:
                r += "Produtos mais comprados por cliente";
                break;
            case Q6:
                r += "N produtos mais vendidos";
                break;
            case Q7:
                r += "Três maiores compradores";
                break;
            case Q8:
                r += "N clientes que compraram mais produtos diferentes";
                break;
            case Q9:
                r += "Clientes que mais compraram um produto";
                break;
            case Q10:
                r += "Faturação total";
                break;
            case Q1_1:
                r += "Stats de ficheiros lidos";
                break;
            case Q1_2:
                r += "Stats globais";
                break;
        }
        return r;
    }

    private void correctMenu() {
        switch (this.menu) {
            case Categories:
                this.options.clear();
                this.options.add(MenuInd.Static);
                this.options.add(MenuInd.Dynamic);
                break;
            case Static:
                this.options.clear();
                this.options.add(MenuInd.Q1_1);
                this.options.add(MenuInd.Q1_2);
                break;
            case Dynamic:
                this.options.clear();
                this.options.add(MenuInd.Q1);
                this.options.add(MenuInd.Q2);
                this.options.add(MenuInd.Q3);
                this.options.add(MenuInd.Q4);
                this.options.add(MenuInd.Q5);
                this.options.add(MenuInd.Q6);
                this.options.add(MenuInd.Q7);
                this.options.add(MenuInd.Q8);
                this.options.add(MenuInd.Q9);
                this.options.add(MenuInd.Q10);
                break;
            case Q1:
                this.options.clear();
                break;
            case Q2:
                this.options.clear();
                break;
            case Q3:
                this.options.clear();
                break;
            case Q4:
                this.options.clear();
                break;
            case Q5:
                this.options.clear();
                break;
            case Q6:
                this.options.clear();
                break;
            case Q7:
                this.options.clear();
                break;
            case Q8:
                this.options.clear();
                break;
            case Q9:
                this.options.clear();
                break;
            case Q10:
                this.options.clear();
                break;
            case Q1_1:
                this.options.clear();
                break;
            case Q1_2:
                this.options.clear();
                break;
        }
    }
}
