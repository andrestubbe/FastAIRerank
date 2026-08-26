# FastAIRerank Engineering Philosophy

## Core Principles

1. **Context Pruning & Prompt Economy**  
   Pruning irrelevant retrieval chunks before passing them to LLMs reduces hallucination rates and slashes prompt token latency.

2. **Cross-Encoder Precision**  
   Evaluates full query-document interactions rather than isolated bi-encoder embeddings for maximum accuracy on nuanced queries.

3. **Zero-Allocation Pipeline**  
   Engineered for sub-millisecond candidate scoring directly within high-frequency agent loops and RAG pipelines.
