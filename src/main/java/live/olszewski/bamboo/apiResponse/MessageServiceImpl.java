package live.olszewski.bamboo.apiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, Locale.getDefault());
    }
}
