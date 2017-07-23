db.request.save({
    animalId: 1,
    clientEmail: "client email",
    history: [
        {
            header: "header message",
            description: "descr request",
            employeeEmail: "employee email",
            requestDate: "2017-07-20",
            messages: [
                {
                    email: "user email",
                    message: "msg",
                    date: "2017-07-20"
                }
            ]

        }
    ]
});


db.sequence.insert(
    {
        _id: "issueInfo",
        seq: 0
    }
);