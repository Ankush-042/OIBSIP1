import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
        accounts.put("user1", new Account("user1", "1234", 5000));
        accounts.put("user2", new Account("user2", "5678", 3000));
        accounts.put("user3", new Account("user3", "9876", 1500));
    }

    public Account authenticate(String userId, String pin) {
        Account account = accounts.get(userId);
        if (account != null && account.authenticate(pin)) {
            return account;
        }
        return null;
    }

    public Account getAccount(String userId) {
        return accounts.get(userId);
    }
}
