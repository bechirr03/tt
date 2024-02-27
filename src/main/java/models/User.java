
package models;


public class User {
     private int id;
    private int CIN;
    private String UserName;
    private int Numero;
    private String Email;
    private String Adresse;
    private String Password;
     private String Roleperm;
    public static User Current_User;
    

    public User(){
    }
    
    public User(int id, int CIN, String UserName, int Numero, String Email, String Adresse, String Password,String Roleperm) {
        this.id = id;
        this.CIN = CIN;
        this.UserName = UserName;
        this.Numero = Numero;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Password = Password;
        this.Roleperm = Roleperm;
    }

    public User(int CIN, String UserName, int Numero, String Email, String Adresse, String Password) {
        this.CIN = CIN;
        this.UserName = UserName;
        this.Numero = Numero;
        this.Email = Email;
        this.Adresse = Adresse;
        this.Password = Password;
    }
     public User(int CIN, String UserName, int Numero, String Email, String Adresse) {
        this.CIN = CIN;
        this.UserName = UserName;
        this.Numero = Numero;
        this.Email = Email;
        this.Adresse = Adresse;
      
    }
      public User(int id,int CIN, String UserName, int Numero, String Email, String Adresse) {
        this.id = id;
        this.CIN = CIN;
        this.UserName = UserName;
        this.Numero = Numero;
        this.Email = Email;
        this.Adresse = Adresse;
      
    }
  public static User getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(User Current_User) {
        User.Current_User = Current_User;
    }

    public User(int aInt, String string, int aInt0, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }
    
    
    
       public String getRoleperm() {
        return Adresse;
    }

    public void setRoleperm(String Roleperm) {
        this.Roleperm = Roleperm;
    }

    
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
   

    @Override
    public String toString() {
        return "Users{" + "CIN=" + CIN + ", UserName=" + UserName + ", Numero=" + Numero + ", Email=" + Email + ", Adresse=" + Adresse + ", Password=" + Password + '}';
    }
    
    
}
