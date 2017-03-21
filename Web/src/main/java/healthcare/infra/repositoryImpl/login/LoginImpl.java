package healthcare.infra.repositoryImpl.login;

import healthcare.domain.login.LoginDto;
import healthcare.domain.login.LoginRepository;
import healthcare.infra.entity.login.DataConnection;

public class LoginImpl extends DataConnection implements LoginRepository{

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
			TypedQuery<HustInfoLogin> query = this.em.createQuery(FIND_DATA_USER, HustInfoLogin.class);
			List<HustInfoLogin> entity = query.setParameter("userName", userName).getResultList();
			if (entity.size() != 0) {
				return convert(entity.get(0));
			}
			return null;
		}

		@Override
		public void insertData(InfoDto dto) {
			this.em.persist(convertEntity(dto));
		}

		@Override
		public void updateData(InfoDto dto) {
			// TODO Auto-generated method stub

		}

		@Override
		public void deleteData(InfoDto dto) {
			// TODO Auto-generated method stub

		}

		@Override
		public InfoDto findLogin(String userName, String passWord) {
			TypedQuery<HustInfoLogin> query = this.em.createQuery(FIND_DATA_LOGIN, HustInfoLogin.class);
			List<HustInfoLogin> entity = query.setParameter("userName", userName).setParameter("password", passWord)
					.getResultList();
			if (entity.size() != 0) {
				return convert(entity.get(0));
			}
			return null;
		}

		@Override
		public InfoDto findUserId(String userId) {
			// TODO Auto-generated method stub
			return null;
		}

		private InfoDto convert(HustInfoLogin entity) {
			InfoDto dto = new InfoDto();
			dto.setVersion(entity.getVersion());
			dto.setUserName(entity.getUserName());
			dto.setUserId(entity.getUserId());
			dto.setStatus(entity.getStatus());
			dto.setPassword(entity.getPassword());
			dto.setAccessCode(entity.getAccessCode());
			return dto;
		}

		private HustInfoLogin convertEntity(InfoDto dto) {
			HustInfoLogin entity = new HustInfoLogin();
			entity.setVersion(dto.getVersion());
			entity.setUserName(dto.getUserName());
			entity.setUserId(dto.getUserId());
			entity.setStatus(dto.getStatus());
			entity.setAccessCode(dto.getAccessCode());
			entity.setPassword(dto.getPassword());
			return entity;
		}

		@Override
		public LoginDto findData(String name) {
			// TODO Auto-generated method stub
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


}
