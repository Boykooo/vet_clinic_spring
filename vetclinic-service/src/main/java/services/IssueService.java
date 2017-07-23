package services;

import dao.IssueDao;
import entities.issue.Issue;
import entities.issue.IssueInfo;
import entities.issue.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.DateManager;

import java.util.List;
import java.util.Objects;

@Service
public class IssueService {

    @Autowired
    private IssueDao issueDao;

    public void addMessage(Long issueInfoId, Message message) {

        Issue foundIssue = issueDao.findByIssueId(issueInfoId);

        if (foundIssue != null) {
            message.setDate(DateManager.getCurrentDate());
            List<IssueInfo> history = foundIssue.getHistory();

            history.stream()
                    .filter(info -> Objects.equals(info.getId(), issueInfoId))
                    .findFirst()
                    .ifPresent(
                            x -> x.addMessage(message)
                    );

            foundIssue.setHistory(history);

            issueDao.save(foundIssue);
        }
    }

    public IssueInfo getIssueInfoById(Long issueInfoId) {
        Issue foundIssue = issueDao.findByIssueId(issueInfoId);
        IssueInfo issueInfo = null;

        if (foundIssue != null) {
            issueInfo = foundIssue.getHistory().stream()
                    .filter(info -> Objects.equals(info.getId(), issueInfoId))
                    .findFirst()
                    .get();
        }
        return issueInfo;
    }
}
