package ata.unit.three.project.expense.lambda;

import ata.unit.three.project.expense.service.DaggerExpenseServiceComponent;
import ata.unit.three.project.expense.service.ExpenseService;
import ata.unit.three.project.expense.service.ExpenseServiceComponent;
import ata.unit.three.project.expense.service.exceptions.ItemNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@ExcludeFromJacocoGeneratedReport
public class RemoveExpenseItemFromList
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    static final Logger log = LogManager.getLogger();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        // Logging the request json to make debugging easier.
        log.info(gson.toJson(input));

        // Your Code Here

        ExpenseServiceComponent dagger = DaggerExpenseServiceComponent.create();
        ExpenseService expenseService = dagger.expenseService();

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        Map<String, String> expenseMap = gson.fromJson(input.getBody(), Map.class);
        String expenseListId = expenseMap.get("expenseListId");
        String expenseItemId = expenseMap.get("expenseItemId");

        try {
            expenseService.removeExpenseItemFromList(expenseListId, expenseItemId);

            return response
                    .withStatusCode(204);
        } catch (ItemNotFoundException e) {
            return response
                    .withStatusCode(400)
                    .withBody(gson.toJson(e.errorPayload()));
        }
    }
}