package app.rest;

import app.responses.BaseResponse;
import app.responses.DataResponse;
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
    public void addMessage(@PathVariable("issueInfoId") Long issueInfoId,
                           @RequestBody Message message) {
        message.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        issueService.addMessage(issueInfoId, message);
    }

    @RequestMapping(value = "/{issueInfoId}", method = RequestMethod.GET)
    public BaseResponse getIssueInfo(@PathVariable("issueInfoId") Long issueInfoId) {
       return new DataResponse<>(issueService.getIssueInfoById(issueInfoId));
    }
}
