import React from "react";

const MessageListItem = ({message}) => {
    return (
        <li className="list-group-item">
            {message}
        </li>
    );
};

export default MessageListItem;