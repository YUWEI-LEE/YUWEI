package tw.com.fcb.mimosa.examples.gettingstarted;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.http.APIErrorException;
import tw.com.fcb.mimosa.tracing.Traced;

@Traced
@RequiredArgsConstructor
@Service
public class ErrorMessageService {
	
	final ErrorMessageRepository errorMsgRepository;
	public ErrorMessage getMessageByCode(String code) {
		
		return errorMsgRepository.findByCode(code)
				.orElseThrow(() -> {
					return new APIErrorException(err -> err.code("ERR0").message("Error code not found"));
				});
	}

	public ErrorMessage createMessage(ErrorMessage errorMsg) {
		return errorMsgRepository.save(errorMsg);
	}
	
}
