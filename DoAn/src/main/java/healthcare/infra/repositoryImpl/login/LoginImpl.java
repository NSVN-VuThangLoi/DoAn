package healthcare.infra.repositoryImpl.login;

import java.util.List;

import javax.ejb.Stateless;

import healthcare.domain.login.LoginDto;
import healthcare.domain.login.LoginRepository;
import healthcare.infra.entity.login.DataConnection;
import healthcare.infra.entity.login.LoginPatient;
@Stateless
public class LoginImpl extends DataConnection implements LoginRepository {

		private static final String FIND_DATA_USER;

		private static final String FIND_DATA_LOGIN;

		static {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT t FROM LoginPatient  t ");
			queryBuilder.append(" WHERE t.userId = :userId ");
			FIND_DATA_USER = queryBuilder.toString();

			queryBuilder = new StringBuilder();
			queryBuilder.append(" SELECT t FROM LoginPatient  t ");
			queryBuilder.append(" WHERE t.userId = :userId ");
			queryBuilder.append(" AND t.password = :password ");
			FIND_DATA_LOGIN = queryBuilder.toString();

		}

		@Override
		public LoginDto findData(String userId) {
			LoginPatient loginPatient =  this.entityManager.createQuery(FIND_DATA_USER,LoginPatient.class).setParameter("userId", userId).getSingleResult();
			return null;
		}

		@Override
		public LoginDto findLogin(String name, String passWord) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public LoginDto findUserId(String userId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void insertData(LoginDto dto) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateData(LoginDto dto) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteData(LoginDto dto) {
			// TODO Auto-generated method stub
			
		}

		

}
