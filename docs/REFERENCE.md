# FastAIRerank API Reference

## Core Engine

### `FastAIRerank`
High-performance semantic relevance scoring and candidate context pruning.

* `rerank(String query, List<Candidate> candidates, int topN)`: Evaluates query relevance across candidate chunks and returns the top-N highest scoring elements.
* `score(String query, Candidate candidate)`: Computes the cross-attention relevance score for an individual candidate.

### `Candidate`
Represents an input context candidate:
* `id()`: Document or passage ID.
* `text()`: Content payload for relevance analysis.
* `initialScore()`: Preliminary rank or distance score.
