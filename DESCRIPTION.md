# FastAIRerank — Ultra-Fast Reranking & Cross-Encoder Relevance Filter for Java

## Core Purpose:
FastAIRerank provides zero-allocation semantic reranking and relevance filtering to bridge the gap between broad candidate retrieval (Top 50-100 from BM25/VectorDB) and token-efficient, high-precision prompt injection (Top 3-5 candidates).

## Key Features:
- **Cross-Encoder Scoring**: In-process neural and lexical cross-scoring of (Query, Document) pairs.
- **Top-N Context Pruner**: Reduces prompt bloat and eliminates hallucination by filtering weak or irrelevant chunks.
- **Zero-Allocation Hot-Path**: Ultra-fast score normalization and sorting without intermediate object churn.
- **Provider & Local Engine Support**: Connects seamlessly with local ONNX/GGUF cross-encoder weights or cloud reranking APIs (Cohere, Jina, SiliconFlow, FastAI).
