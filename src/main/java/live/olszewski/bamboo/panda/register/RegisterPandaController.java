package live.olszewski.bamboo.panda.register;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping(path = "#{apiConfig.registerPandaPath}")
@Tag(name = "Register Panda", description = "Endpoints related to register new panda device")
public class RegisterPandaController {

    @Autowired
    private RegisterPandaService registerPandaService;

    @PostMapping
    @Operation(
            description = "Register new panda device",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Device with this parameters already exists"
                    )
            }
    )
    @Parameter(
            name = "registerPanda",
            description = "RegisterPanda object",
            required = true,
            example = "{\"name\":\"Panda\",\"location\":\"Warsaw\"}"

    )
    public void registerNewPandaDevice(@RequestBody RegisterPanda registerPanda) {
        registerPandaService.addPandaDevice(registerPanda);
    }

    @DeleteMapping(path = "{pandaId}")
    @Operation(
            description = "Delete panda device",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Device with id {pandaId} does not exists"
                    )
            }
    )
    @Parameter(
            name = "pandaId",
            description = "Panda device id",
            required = true,
            example = "1"
    )
    public void deletePandaDevice(@PathVariable("pandaId") Long id) {
        registerPandaService.deletePandaDevice(id);
    }

}
