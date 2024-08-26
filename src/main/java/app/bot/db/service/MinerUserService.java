package app.bot.db.service;

import app.bot.db.model.MinerUser;
import app.bot.db.repository.MinerUserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@Service
public class MinerUserService {
    private final MinerUserRepository minerUserRepository;

    public MinerUserService(MinerUserRepository minerUserRepository) {
        this.minerUserRepository = minerUserRepository;
    }

    public Optional<MinerUser> findVpnUserByChatId(Long chatId) {
        return minerUserRepository.findById(chatId);
    }

    public void save(MinerUser user) {
        minerUserRepository.save(user);
    }

    public List<MinerUser> findAll(){
        return minerUserRepository.findAll();
    }

    public void updateUserData(Long chatId, Update update, boolean hasMessage) {
        MinerUser user = findVpnUserByChatId(chatId).orElse(new MinerUser(update, hasMessage));
        user.setLastVisit(System.currentTimeMillis());
        minerUserRepository.save(user);
    }
}
