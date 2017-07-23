package app.rest;

import app.responses.BaseResponse;
import app.responses.DataResponse;
import app.responses.SuccessResponse;
import entities.issue.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.IssueService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/issue", produces = "application/json")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @RequestMapping(value = "/{issueInfoId}", method = RequestMethod.POST)
    public SuccessResponse addMessage(@PathVariable("issueInfoId") Long issueInfoId,
                                      @RequestBody Message message) {
        message.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        issueService.addMessage(issueInfoId, message);

        return new SuccessResponse();
    }

    @RequestMapping(value = "/{issueInfoId}", method = RequestMethod.GET)
    public BaseResponse getIssueInfo(@PathVariable("issueInfoId") Long issueInfoId) {
       return new DataResponse<>(issueService.getIssueInfoById(issueInfoId));
    }

    @RequestMapping(value = "/animal/{id}", method = RequestMethod.GET)
    public BaseResponse getByAnimalId(@PathVariable("id") Integer id) {
        return new DataResponse<>(issueService.findIssueInfoByAnimalid(id));
    }

    @RequestMapping(value = "/lastChange", method = RequestMethod.GET)
    public BaseResponse getByEmployeeEmail() {
        return new DataResponse<>(issueService.findLastChangedByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @RequestMapping(value = "/email/{id}", method = RequestMethod.GET)
    public BaseResponse getAllByIdAndEmail(@PathVariable("id") Integer id) {
        return new DataResponse<>(issueService.findByIdAndEmail(id, SecurityContextHolder.getContext().getAuthentication().getName()));
    }

}
