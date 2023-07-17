package com.example.demo.persistance.dao;


import com.example.demo.model.Utente;


public interface UtenteDAO {

	public boolean save(Utente utente);  // Create  == insert/store
	public Utente findByPrimaryKey(String codiceFiscale);     // Retrieve  == select stud
	/*public List<Utente> findAll();     //  select*
	public List<Utente> findAllOtherUsers(String username);     //  select*
	*/
	public boolean updateUtente(Utente u); //Update
	/*public void delete(String username); //Delete	
	public String findUsername(String email);
	*/public boolean checkPassword(String codiceFiscale, String password);
	//public String getUsername(String email);
	public boolean existsUser(String codiceFiscale);
	 public boolean existsUserEmail(String email); 
	 public void setPassword(String codicefiscale, String password);
	 /* //Update public List<Utente> findByName(String cercaNome); // Retrieve ==
	 * select stud public Utente getUtente(String username); public String
	 * getEmail(String username);
	 */
	public boolean updateWithoutPsw(Utente u);
	public String getCodiceFiscale(String email);
}
