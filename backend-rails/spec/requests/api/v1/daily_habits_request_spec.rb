require 'rails_helper'

describe 'api/v1/daily_habits', type: :request do
  describe '#index' do
    let(:user) { create(:user, group:) }
    let(:group) { create(:group) }
    let!(:habit) { create(:habit, group:) }
    let!(:daily_habits) { create_list(:daily_habit, 2, user:, group:) }
    let!(:daily_habits2) { create_list(:daily_habit, 3, group:) }

    before { get api_v1_daily_habits_path, headers: auth_headers, as: :json }
    it 'returns status 200 ok' do
      expect(response).to be_successful
    end

    it "returns user's daily_habits" do
      expect(json_response.pluck('id')).to match_array(daily_habits.pluck(:id) + [nil])
    end
  end

  describe '#create' do
    let(:group) { create(:group) }
    let(:user) { create(:user, group:) }
    let(:habit) { create(:habit, group:) }
    let(:daily_habit) { build(:daily_habit, user:, group:, habit:) }

    let(:daily_habits_params) do
      {
        habit_id: habit.id,
        check:
      }
    end

    subject do
      post api_v1_daily_habits_path, params: daily_habits_params, headers: auth_headers, as: :json
    end

    describe 'when it is valid' do
      let(:check) { true }

      it 'returns status 201 created' do
        subject
        expect(response).to be_created
      end

      it 'creates a daily_habit' do
        expect { subject }.to change { DailyHabit.count }.from(0).to(1)
      end

      it 'returns formatted daily_habit' do
        subject
        expect(json_response).to match({
                                         'id' => anything,
                                         'check' => check,
                                         'date' => Time.zone.now.strftime('%F'),
                                         'habit_icon' => nil,
                                         'habit_name' => habit.name
                                       })
      end
    end
  end
end
