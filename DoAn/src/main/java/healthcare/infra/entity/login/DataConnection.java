package healthcare.infra.entity.login;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.Data;
@Data
public class DataConnection {
	@PersistenceContext(unitName = "DOAN")
	public EntityManager entityManager;
}
