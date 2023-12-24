package live.olszewski.bamboo.panda.register;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;


public interface RegisterPandaService {

    ResponseEntity<ApiResponseDto<?>> addPandaDevice(RegisterPanda registerPanda);

    ResponseEntity<ApiResponseDto<?>> deletePandaDevice(Long id);

}
