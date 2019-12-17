Implementation details:

UserDocuments is a map that contains which user have access to the document
When someone creates new document it gets new id by increment a static id var 

IDocument is an interface that expected to be used in a service

The main idea of this implementation is separation of the whole text into pieces (blocks) and we do not need
to refresh it entirely every time when changes come. Also assuming that blocks will be edited and their sizes will be 
changed, we create a buffer(filled by whitespaces) and assuming that frontend could skip it (not show but keep it) and 
know about blocks indexes. We also consider changes to be atomic (change/delete/add_tail could be processed separately only)
When someone adds new text into existing block we just need it to be refreshed. When block becomes greater than defined 
block size we use buffer, it it still greater we move next block and add the rest at the beginning and continue it
until there are free buffers. If not we add new block at the end with rest part of text and new buffer. Once (if there
are lots of new addition in the center or beginning of document) there will be only buffers at the end, so we could
restructure it in the any convenient time (write some scheduled util for this purpose).

The changes stores in Versions it contains version counter and map of the changes that stores order,
there are 3 types of changes: addition (means tail addition), removing, editing. Depending on the type and changes order frontend will
understand what to do by provided block index.

We also assume that there is some function on the frontend side that checks if there are some updates every second
(more/less). We also could use some listener that listen topic from some message broker (kafka) or use SSE.