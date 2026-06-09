package com.vedaai.rag.service.ranking.impl;

import org.springframework.stereotype.Service;

import com.vedaai.rag.service.ranking.RankingService;

@Service
public class RankingServiceImpl
        implements RankingService {

    @Override
    public Double calculateSimilarity(Double distance) {

        return Math.max(0.0, 1 - distance);
    }
}