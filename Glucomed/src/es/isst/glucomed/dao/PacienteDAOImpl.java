package es.isst.glucomed.dao;

import javax.persistence.EntityManager;

import es.isst.glucomed.model.Paciente;
import es.isst.glucomed.model.User;


public class PacienteDAOImpl implements PacienteDAO {

	private static PacienteDAOImpl instance;
	
	private PacienteDAOImpl(){
		
	}
	
	public static  PacienteDAOImpl getInstance(){
		if(instance==null)
			instance = new PacienteDAOImpl();
		return instance;
	}

	@Override
	public boolean insertData(User user, String fecha, String hora,
			String valorGlucosa) {
		UserDAO dao = (UserDAO) UserDAOImpl.getInstance(); //coger del session
		
		boolean testUser = dao.SuccessLogin (user.getEmail(),user.getPassword());
		
		Paciente paciente = new Paciente (user.getNombre(), fecha, hora, valorGlucosa);
		
		if (testUser) { //no hace falta		
			EntityManager em = EMFService.get().createEntityManager();
			em.persist(paciente);
			em.close(); 
			return true;
		} else {
			return false;
		}
	}

}