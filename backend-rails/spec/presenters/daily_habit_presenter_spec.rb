# frozen_string_literal: true

require 'rails_helper'

RSpec.describe DailyHabitPresenter do
  describe '#payload' do
    let!(:daily_habit) { create(:daily_habit) }
    subject { described_class.new(daily_habit:).payload }

    it 'matches expected attributes' do
      expected_payload = {
        id: daily_habit.id,
        date: daily_habit.date,
        check: daily_habit.check,
        habit_name: daily_habit.habit.name,
        habit_icon: daily_habit.habit.icon
      }

      is_expected.to eq expected_payload
    end
  end
end
