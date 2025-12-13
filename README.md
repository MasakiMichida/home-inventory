# 買い物楽になる在庫管理アプリ

## 概要
買い物に行くときのメモ作成が面倒なので、よく買うものを登録して「数量を更新するだけ」で買い物リストを作れるようにするアプリです。

## 主な機能（MVP）
- よく買う品目の登録（名前/カテゴリ/メモ など）
- 在庫数の更新（増減）
- 買い物リストの表示（在庫が閾値以下のものを抽出 等）
- ログイン（E2Eでログイン→CRUDを確認）

## 技術スタック
- Frontend: React + TypeScript
- API: Spring Boot（OpenAPI / Swagger UI）
- DB: PostgreSQL
- Migration: Flyway（またはLiquibase）
- Test
  - Unit: JUnit5 + Mockito
  - Integration: Testcontainers（Postgresを起動して実DBで検証）
  - E2E: Playwright（ログイン→登録→一覧→更新）
- CI: GitHub Actions（lint/format → test → build）
- Reproducibility: docker compose で「フロント + API + DB」を一発起動

## 開発目的
モダンな開発環境で、堅牢なアプリケーションの作成方法を学ぶため。
（例：DBマイグレーション、テスト戦略、CI、E2E、コンテナでの再現性）