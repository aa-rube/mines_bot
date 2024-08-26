package app.bot.db.repository;

import app.bot.db.model.MinerUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinerUserRepository extends MongoRepository<MinerUser, Long> {
}
