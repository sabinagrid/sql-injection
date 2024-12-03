### **Statement vs PreparedStatement**

**Statement**:
- Prone to SQL injection as queries are constructed dynamically by appending user inputs.

**PreparedStatement**:
- Prevents SQL injection by escaping input values automatically.


#### **Advantages of PreparedStatement**:
1. Mitigates SQL injection
2. Improves performance for repeated queries

#### Results using Statement (Vulnerable):
`SELECT * FROM users WHERE name = '' OR '1'='1';`
always evaluates to true, causing the query to return all rows from the users table, regardless of the actual name values.

This demonstrates the vulnerability of Statement to SQL injection.

#### Results using PreparedStatement (Secure):
`SELECT * FROM users WHERE name = ?;`

The user input (' OR '1'='1) is treated as a literal string, not executable SQL.
The query attempts to find a user with the exact name "' OR '1'='1", which likely doesn't exist in your database, so no rows are returned.

This demonstrates that PreparedStatement mitigates SQL injection by treating user input as data, not executable SQL.
