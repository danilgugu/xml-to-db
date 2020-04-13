package danil.xmltodb.controller;

import danil.xmltodb.logic.GetItemsByColorAndContainedInOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ItemsApi;
import org.openapitools.model.Body;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${openapi.xmlToDbApp.base-path:/boxApi}")
public class ItemController implements ItemsApi {

    private final GetItemsByColorAndContainedInOperation operation;

    @Override
    public ResponseEntity<List<String>> getItemsByColorContainedInBoxRecursively(@ApiParam(value = "", required=true)  @Valid @RequestBody Body body) {
        List<String> itemIds = operation.process(body.getColor(), body.getContainedIn());
        return ResponseEntity.ok(itemIds);
    }
}
