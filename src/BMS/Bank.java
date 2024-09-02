
package BMS;

import java.util.*;
import java.io.*;
public class Bank {
    
    private Map <String , Account> accounts = new HashMap<>();
    private final String FILE_PATH = "data/accounts.ser";
    
    public Bank (){
        
    }
    
    public void createAccount (Account account){
        accounts.put(account.getAccountNumber(), account);
        saveAccounts();
        
    }
    
    public void deleteAccount (String accountNumber) throws AccountNotFoundException{
        if (accounts.containsKey(accountNumber)) {
            accounts.remove(accountNumber);
            saveAccounts();
        } else {
            throw new AccountNotFoundException("Account number : " + accountNumber + " not found.");
        }
    }
    
    public Account searchAccount (String accountNumber) throws AccountNotFoundException{
        if (accounts.containsKey(accountNumber)) {
            return accounts.get(accountNumber);
        } else {
            throw new AccountNotFoundException("Account number : " + accountNumber + " not found.");
        }
    }
    
     public List<Account> searchAccountByName(String name) {
        List<Account> results = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.getName().equalsIgnoreCase(name)) {
                results.add(account);
            }
        }
        return results;
    }
    
    public void saveAccounts(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(accounts);
            
        } catch (IOException e ){
            System.out.println("Error saving accounts : " + e.getMessage());
        }
    }
    
    public void loadAccounts(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            accounts = (Map<String, Account>) in.readObject();
        } catch (FileNotFoundException e){
            System.out.println("No previous account found, Starting fresh.");
        } catch (IOException  | ClassNotFoundException e){
            System.out.println("Error loading accounts : " + e.getMessage());
        }
    }
    
    
}
