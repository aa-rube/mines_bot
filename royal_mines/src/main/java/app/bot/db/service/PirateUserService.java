package app.bot.db.service;

import app.bot.db.model.PirateUser;
import app.bot.db.repository.MinerUserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@Service
public class PirateUserService {
    private final MinerUserRepository minerUserRepository;

    public PirateUserService(MinerUserRepository minerUserRepository) {
        this.minerUserRepository = minerUserRepository;
    }

    public Optional<PirateUser> findVpnUserByChatId(Long chatId) {
        return minerUserRepository.findById(chatId);
    }

    public void save(PirateUser user) {
        minerUserRepository.save(user);
    }

    public List<PirateUser> findAll(){
        return minerUserRepository.findAll();
    }

    public void updateUserData(Long chatId, Update update, boolean hasMessage) {
        PirateUser user = findVpnUserByChatId(chatId).orElse(new PirateUser(update, hasMessage));
        user.setLastVisit(System.currentTimeMillis());
        minerUserRepository.save(user);
    }
}
