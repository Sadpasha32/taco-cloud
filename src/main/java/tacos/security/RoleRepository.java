package tacos.security;

import org.springframework.data.repository.CrudRepository;
import tacos.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {
}
