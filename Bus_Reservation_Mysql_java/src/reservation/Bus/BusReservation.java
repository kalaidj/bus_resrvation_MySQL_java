package reservation.Bus;
import java.sql.*;
import java.util.Scanner;
import static java.lang.System.exit;

public class BusReservation {

    public static void BookTicket(){
        final String url = "jdbc:mysql://localhost:3306/busdb";
        final String uname = "root";
        final String pass = "root";
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\t\t*Please Remember 'Your ID' number for further Updatation.....");
        System.out.print("\t\t\tEnter Passenger ID number : ");
        int id = sc.nextInt();
        System.out.print("\t\t\tEnter Passenger name : ");
        String name = sc.next();
        System.out.print("\t\t\tEnter Passenger Source : ");
        String source = sc.next();
        System.out.print("\t\t\tEnter Passenger Destination : ");
        String destination = sc.next();


        String query="INSERT INTO bus (id, name, source, destination) values ('"+id+"','"+name+"','"+source+"','"+destination+"')";
        Connection con;

        try{
            con=DriverManager.getConnection(url,uname,pass);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);
            if(rows==1) {
                System.out.println("\t\t\t your seat has booked !....");
            }else {
                System.out.println("\t\t\tSomething is Wrong.....!! ");
            }
            Home();


        }catch (Exception e){
            e.printStackTrace();

        }

    }
    public static void ViewDetail(){
        final String url = "jdbc:mysql://localhost:3306/busdb";
        final String uname = "root";
        final String pass = "root";
        String query = "select * from bus";

        Connection con;
        try {
            con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String source = rs.getString("source");
                String destination = rs.getString("destination");

                System.out.println("\t\t\tpass ID : "+id+"  Name -->> "+name+"    Source -->> "+source+"    Destination -->> "+destination);
            }
            Home();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void CancelJourney(){
        final String url ="jdbc:mysql://localhost:3306/busdb";
        final String uname = "root";
        final String pass = "root";
        System.out.print("\t\t\tEnter passenger ID number : ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        String query = "DELETE  FROM bus where id ='"+id+"' ";

        Connection con;
        try{
            con = DriverManager.getConnection(url,uname,pass);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);
            if(rows==1) {
                System.out.println("\t\t\t your ticket has been canceled....");
            }else {
                System.out.println("\t\t\tPlease enter correct ID number.....!! ");
                CancelJourney();
            }
            Home();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void updateData(){
        final String url ="jdbc:mysql://localhost:3306/busdb";
        final String uname = "root";
        final String pass = "root";
        String query = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\t\t1 - ID no. Updates");
        System.out.println("\t\t\t2 - Name Updates");
        System.out.println("\t\t\t3 - source Updates");
        System.out.println("\t\t\t4 - Destination Updates");
        System.out.print("\t\t Choose Option to Updates data -->> ");
        int opt = sc.nextInt();
        if(opt==1){
            System.out.print("\n\t\t\tEnter name of passenger : ");
            String name = sc.next();
            System.out.print("\t\t\tEnter to updates ID number : ");
            int id = sc.nextInt();
            query = "UPDATE bus SET id ='"+id+"' where name ='"+name+"' ";
        }else if(opt==2){
            System.out.print("\n\t\t\tEnter passenger ID number : ");
            int id = sc.nextInt();
            System.out.print("\t\t\tEnter Names to Updates  : ");
            String name = sc.next();
            query = "UPDATE bus SET name ='"+name+"' where id ='"+id+"' ";
        }
        else if(opt==3){
            System.out.print("\n\t\t\tEnter employee ID number : ");
            int id = sc.nextInt();
            System.out.print("\t\t\tEnter source to updates  : ");
            String source = sc.next();
            query = "UPDATE bus SET source ='"+source+"' where id ='"+id+"' ";

        }
        else if(opt==4){
            System.out.print("\n\t\t\tEnter passenger ID number : ");
            int id = sc.nextInt();
            System.out.print("\t\t\tEnter Destination to Updates  : ");
            String destination = sc.next();
            query = "UPDATE bus SET destination ='"+destination+"' where id ='"+id+"' ";
        }
        else {
            System.out.println("\n\t\t\tChoose correct option ......");
            updateData();
        }
        Connection con;
        try{
            con = DriverManager.getConnection(url,uname,pass);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);
            if(rows==1) {
                System.out.println("\t\t\t\t\t your data has been updated....");
            }else {
                System.out.println("\t\t\tPlease enter correct data .....!! ");
                updateData();
            }
            Home();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void Home() {
        System.out.println("\n\n\t\t\t --------- Welcome To Green Bus--------\n");
        System.out.println("\t\t\t1 - book ticket ");
        System.out.println("\t\t\t2 - your details ");
        System.out.println("\t\t\t3 - Update detals ");
        System.out.println("\t\t\t4 - cancel journey ");
        System.out.println("\t\t\t5 - Exit");
        System.out.print("\t\t Select Option  ---->>>  ");
        Scanner sc = new Scanner(System.in);
        int select= sc.nextInt();
        switch (select) {
            case 1 -> {
                System.out.println("\n\n\t\t\t ------ Book your Tickets Carefully ------\n");
                BookTicket();
            }
            case 2 -> {
                System.out.println("\n\n\t\t\t\t\t ---------- Passenger Details ---------\n");
                ViewDetail();
            }
            case 3 -> {
                System.out.println("\n\n\t\t\t\t\t --------- Update your Journey ---------\n");
                updateData();
            }
            case 4 -> {
                System.out.println("\n\n\t\t\t\t\t ---------- Delete Your Journey ----------\n");
                CancelJourney();
            }
            case 5 -> {
                System.out.println("\t\t\t\t\t   Exiting....\n \t\t\t\t\t Thank You...♥");
                exit(0);
            }
            default -> {
                System.out.println("Wrong Option...!");
                Home();
            }
        }

    }
    public static void main(String[] args) {
        Home();

    }
}
